package com.alura.ChallengeForoHub.controller;

import com.alura.ChallengeForoHub.domain.curso.Curso;
import com.alura.ChallengeForoHub.domain.curso.CursoRepository;
import com.alura.ChallengeForoHub.domain.topico.*;
import com.alura.ChallengeForoHub.domain.usuario.Usuario;
import com.alura.ChallengeForoHub.domain.usuario.UsuarioRepository;
import com.alura.ChallengeForoHub.infra.errors.TratadorDeErrores;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                                          UriComponentsBuilder uriComponentsBuilder) {
        // Buscar autor y curso en la base de datos
        Usuario autor = usuarioRepository.findById(datosRegistroTopico.autor()).orElseThrow(() -> new RuntimeException("Autor no encontrado"));
        Curso curso = cursoRepository.findById(datosRegistroTopico.curso()).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        // Crear el tópico
        Topico topico = new Topico(datosRegistroTopico, autor, curso);
        topicoRepository.save(topico);
        // Crear DTO de respuesta
        DatosRespuestaTopico respuesta = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getFecha_creacion(), topico.getStatus(), topico.getAutor().getNombre(), topico.getCurso().getNombre()
        );
        // Construir URL del recurso creado
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listarTopicos(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosListadoTopico::new));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        // Buscar el tópico existente
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new TratadorDeErrores.RecursoNoEncontradoException("Tópico con ID " + id + " no encontrado"));

        // Usar el autor actual si no se proporciona uno nuevo
        Usuario autor = datosActualizarTopico.autor() != null ? usuarioRepository.findById(datosActualizarTopico.autor())
                .orElseThrow(() -> new TratadorDeErrores.RecursoNoEncontradoException("Autor con ID " + datosActualizarTopico.autor() + " no encontrado")) : topico.getAutor();

        // Usar el curso actual si no se proporciona uno nuevo
        Curso curso = datosActualizarTopico.curso() != null ? cursoRepository.findById(datosActualizarTopico.curso())
                .orElseThrow(() -> new TratadorDeErrores.RecursoNoEncontradoException("Curso con ID " + datosActualizarTopico.curso() + " no encontrado")): topico.getCurso();

        // Actualizar los datos del tópico
        topico.actualizarDatos(datosActualizarTopico, autor, curso);
        // Crear la respuesta
        DatosRespuestaTopico respuesta = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getFecha_creacion(), topico.getStatus(), topico.getAutor().getNombre(), topico.getCurso().getNombre()
        );
        return ResponseEntity.ok(respuesta);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        // Verificar si el tópico existe
        if (!topicoRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // Retornar 404 si no existe
        }

        // Eliminar el tópico usando deleteById
        topicoRepository.deleteById(id);

        // Retornar una respuesta exitosa
        return ResponseEntity.noContent().build(); // HTTP 204
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> retornaDatosTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getFecha_creacion(), topico.getStatus(), topico.getAutor().getNombre(), topico.getCurso().getNombre());

        return ResponseEntity.ok(datosTopico);
    }



}













