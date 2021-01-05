package com.CSI5324;

import com.CSI5324.model.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class OntologyControllerTests extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }



    @Test
    public void createOntologyTest() throws Exception {

        List<Long> edgeList = new LinkedList<>();
        edgeList.add(1L);
        edgeList.add(2L);
        edgeList.add(3L);
        List<Long> nodeList = new LinkedList<>();
        nodeList.add(1L);
        nodeList.add(2L);
        nodeList.add(3L);
        nodeList.add(4L);

        Ontology testOntlogoy = createOntology("testOntlogoy", "2020-11-29 19:05:45.271", 1L, edgeList, nodeList);

        String uri = "/oms/ontology/create";
        String inputJson = super.mapToJson(testOntlogoy);
        System.out.println(inputJson);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
    }

    @Test
    public void deleteOntologyTest() throws Exception {
            String uri = "/oms/ontology/deleteById?id=1";
            MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
            int status = mvcResult.getResponse().getStatus();
            assertEquals(200, status);
            String content = mvcResult.getResponse().getContentAsString();
            System.out.println(content);
    }

    @Test
    public void updateOntologyTest() throws Exception {

        String uri = "/oms/ontology/findById?id=1";
        try {
            MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
            System.out.println(mvcResult.getResponse().getContentAsString());
            Ontology ontology1 = super.mapFromJson(mvcResult.getResponse().getContentAsString(), Ontology.class);
            if (ontology1 != null) {

                ontology1.setName("new name");

                String uri1 = "/oms/ontology/update";

                String inputJson = super.mapToJson(ontology1);
                MvcResult mvcResult1 = mvc.perform(MockMvcRequestBuilders.post(uri1)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson)).andReturn();

                int status = mvcResult1.getResponse().getStatus();
                assertEquals(200, status);
                String content = mvcResult1.getResponse().getContentAsString();
                System.out.println(content);
            }
        }catch (Exception e){

        }
    }
}


