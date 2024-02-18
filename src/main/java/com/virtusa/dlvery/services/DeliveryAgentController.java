package com.virtusa.dlvery.services;

import com.virtusa.dlvery.entities.DeliveryAgent;
import com.virtusa.dlvery.exceptions.DeliveryAgentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/agents")
public class DeliveryAgentController {

    @Autowired
    private DeliveryAgentService agentService;

    @GetMapping
    public ResponseEntity<List<DeliveryAgent>> getAllAgents() {
        List<DeliveryAgent> agents = agentService.getAllAgents();
        return new ResponseEntity<>(agents, HttpStatus.OK);
    }

    @GetMapping("/{agentId}")
    public ResponseEntity<DeliveryAgent> getAgentById(@PathVariable UUID agentId) {
        try {
            DeliveryAgent agent = agentService.getAgentById(agentId);
            return new ResponseEntity<>(agent, HttpStatus.OK);
        } catch (DeliveryAgentNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<DeliveryAgent> createAgent(@RequestBody DeliveryAgent agent) {
        try {
            DeliveryAgent createdAgent = agentService.createAgent(agent);
            return new ResponseEntity<>(createdAgent, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{agentId}")
    public ResponseEntity<DeliveryAgent> updateAgent(@PathVariable UUID agentId, @RequestBody DeliveryAgent agent) {
        try {
            DeliveryAgent updatedAgent = agentService.updateAgent(agentId, agent);
            return new ResponseEntity<>(updatedAgent, HttpStatus.OK);
        } catch (DeliveryAgentNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{agentId}")
    public ResponseEntity<Void> deleteAgent(@PathVariable UUID agentId) {
        try {
            agentService.deleteAgent(agentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DeliveryAgentNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

