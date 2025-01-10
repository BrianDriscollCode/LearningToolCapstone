package com.example.LifeLongLearningTool.Service;

import com.example.LifeLongLearningTool.Dao.ReferenceMaterialRepository;
import com.example.LifeLongLearningTool.Dao.TopicRepository;
import com.example.LifeLongLearningTool.Entity.ReferenceMaterial;
import com.example.LifeLongLearningTool.Entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferenceMaterialService {

    @Autowired
    private ReferenceMaterialRepository referenceMaterialRepository;

    @Autowired
    TopicRepository topicRepository;

    public List<ReferenceMaterial> getReferenceMaterial(Long topicID)
    {
        System.out.println("a");
        Topic topic = topicRepository.getReferenceById(topicID);
        System.out.println("b");
        return referenceMaterialRepository.findByTopic(topic);
    }

    public void postReferenceMaterial(String name, String location, Long topicID)
    {
        Topic topic = topicRepository.getReferenceById(topicID);

        ReferenceMaterial rm = new ReferenceMaterial();
        rm.setName(name);
        rm.setLocation(location);
        rm.setTopic(topic);

        referenceMaterialRepository.save(rm);
    }

    public void editReferenceMaterial(String name, String location, Long referenceID)
    {
        ReferenceMaterial rm =  referenceMaterialRepository.getReferenceById(referenceID);
        System.out.println(rm);
        rm.setName(name);
        rm.setLocation(location);
        System.out.println(rm + "2");
        referenceMaterialRepository.save(rm);
    }

    public void deleteReferenceMaterial(Long referenceID)
    {
        ReferenceMaterial rm = referenceMaterialRepository.getReferenceById(referenceID);
        referenceMaterialRepository.delete(rm);
    }

}
