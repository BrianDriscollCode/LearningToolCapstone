package com.example.LifeLongLearningTool.Controller;

import com.example.LifeLongLearningTool.Dto.ReferenceMaterialObjectDTO;
import com.example.LifeLongLearningTool.Entity.ReferenceMaterial;
import com.example.LifeLongLearningTool.Service.ReferenceMaterialService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/referenceMaterial")
public class ReferenceMaterialController {

    @Autowired
    ReferenceMaterialService referenceMaterialService;

    @GetMapping("/get/{topicID}")
    public List<ReferenceMaterial> getReferenceMaterial(@PathVariable Long topicID)
    {

        System.out.println("1");
        List<ReferenceMaterial> rm =  referenceMaterialService.getReferenceMaterial(topicID);
        System.out.println("2");
        for (ReferenceMaterial mat: rm)
        {
            System.out.println(mat + " -RM");
        }
        System.out.println("3");

        return rm;
    }

    @PostMapping("/post")
    public ResponseEntity<String> postReferenceMaterialObject(@RequestBody ReferenceMaterialObjectDTO referenceMaterialObjectDTO)
    {
        referenceMaterialService.postReferenceMaterial(referenceMaterialObjectDTO.getName(), referenceMaterialObjectDTO.getLocation(), referenceMaterialObjectDTO.getTopicID());
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/edit/{referenceID}/{name}/{location}")
    public ResponseEntity<String> editReferenceMaterial(@PathVariable Long referenceID, @PathVariable String name, @PathVariable String location)
    {
        referenceMaterialService.editReferenceMaterial(name, location, referenceID);
        System.out.println("REFERENCE");
        return ResponseEntity.ok("Success");
    }

    @DeleteMapping("/delete/{referenceID}")
    public ResponseEntity<String> deleteReferenceMaterial(@PathVariable Long referenceID)
    {
        referenceMaterialService.deleteReferenceMaterial(referenceID);
        return ResponseEntity.ok("Success");
    }

}
