package intership.project.certificategenapi.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TemplateService {

    public ResponseEntity<List<String>> listTemplates() throws IOException {
        List<String> templates = new ArrayList<>();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        Resource[] resources = resolver.getResources("classpath:/templates/*");

        for (Resource resource : resources) {
            templates.add(resource.getFilename());
        }

        return ResponseEntity.ok(templates);
    }
}
