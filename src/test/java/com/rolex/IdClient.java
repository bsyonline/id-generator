package com.rolex;

import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/*******************************************************************************
 * - Copyright (c)  2018  chinadaas.com
 *   @author rolex
 * - File Name: com.rolex.service.IdClient
 * - Description:
 *
 *
 * - Function List:
 *
 *
 * - History:
 * Date         Author          Modification
 * 2019/01/07   rolex           Create file
 *******************************************************************************/
@Data
public class IdClient {
    
    Queue<Long> idQueue = Queues.newConcurrentLinkedQueue();
    
    Map<Long, Queue<Long>> block = Maps.newConcurrentMap();
    
    long blockId = 0;
    float capacity = 20f;
    
    public IdClient() {
        fill(0L);
        new Timer("IdChecker").schedule(task, 0L, 1500L);
    }
    
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            System.out.println(new Date() + " id 剩余 " + size() + ", " + size() / (capacity));
            long nextBlockId = nextBlockId();
            fill(nextBlockId);
        }
    };
    
    
    public synchronized Long getId() {
        if (block.get(blockId) == null) {
            fill(blockId);
        }
        if (block.get(blockId).size() == 0) {
            System.out.println("block " + blockId + " 的 id 已用完");
            block.remove(blockId);
            this.blockId = nextBlockId();
            fill(blockId);
        }
        return block.get(blockId).poll();
    }
    
    
    private void fill(Long blockId) {
        
        if (size() / capacity < 0.25) {
            System.out.println(new Date() + "id 不足 " + size() / capacity);
            
            // request id from id service
            RestTemplate restTemplate = new RestTemplate();
            String idset = restTemplate.exchange("http://localhost:8080/blocks/ids", HttpMethod.GET, null, String.class).getBody();
            Gson gson = new Gson();
            Set<Long> ids = gson.fromJson(idset, new TypeToken<HashSet<Long>>() {
            }.getType());
            Queue queue = Queues.newConcurrentLinkedQueue();
            queue.addAll(ids);
            block.put(blockId, queue);
            System.out.println("从 id service 获取 id ：" + ids);
        }
    }
    
    private long size() {
        int cap = 0;
        if (block.get(blockId) != null) {
            cap = block.get(blockId).size();
        }
        return cap + backupSize();
    }
    
    private long backupSize() {
        int cap = 0;
        if (block.get(nextBlockId()) != null) {
            cap = block.get(nextBlockId()).size();
        }
        return cap;
    }
    
    private long nextBlockId() {
        return blockId + 1;
    }
    
    
    public static void main(String[] args) {
        IdClient idClient = new IdClient();
        for (int i = 0; i < 100; i++) {
            System.out.println(idClient.getId());
        }
    }

}
