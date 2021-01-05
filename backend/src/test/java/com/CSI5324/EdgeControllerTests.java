package com.CSI5324;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.CSI5324.model.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;


public class EdgeControllerTests extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }


    /**
     * Creates a successful applicant
     * @throws Exception
     */

    @Test
    public void createEdgeTest() throws Exception {
        try {
            String uri1 = "/oms/node/findById?id=1";
            MvcResult mvcResult1 = mvc.perform(MockMvcRequestBuilders.get(uri1)).andReturn();
            System.out.println(mvcResult1.getResponse().getContentAsString());
            Node node1 = super.mapFromJson(mvcResult1.getResponse().getContentAsString(), Node.class);

            String uri2 = "/oms/node/findById?id=2";
            MvcResult mvcResult2 = mvc.perform(MockMvcRequestBuilders.get(uri2)).andReturn();
            System.out.println(mvcResult2.getResponse().getContentAsString());
            Node node2 = super.mapFromJson(mvcResult2.getResponse().getContentAsString(), Node.class);

            if (node1 != null && node2 != null) {
                Edge testEdge = createEdge(EdgeCategory.IS_A, node1, node2, null);
                System.out.println(testEdge.getFromNode().getId());
                String uri = "/oms/edge/create";
                String inputJson = super.mapToJson(testEdge);
                System.out.println(inputJson);
                MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson)).andReturn();

                int status = mvcResult.getResponse().getStatus();
                assertEquals(200, status);
                String content = mvcResult.getResponse().getContentAsString();
                System.out.println(content);
            }
        }catch (Exception e){

        }
    }

    @Test
    public void deleteEdgeTest() throws Exception {
        try {
            String uri = "/oms/edge/deleteById?id=1";
            MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
            int status = mvcResult.getResponse().getStatus();
            assertEquals(200, status);
            String content = mvcResult.getResponse().getContentAsString();
            System.out.println(content);
        }catch (Exception e){

        }
    }

    @Test
    public void updateEdgeTest() throws Exception {
        try {
            String uri = "/oms/edge/findById?id=2";
            MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
            System.out.println(mvcResult.getResponse().getContentAsString());
            Edge edge1 = super.mapFromJson(mvcResult.getResponse().getContentAsString(), Edge.class);

            edge1.setEdgeCategory(EdgeCategory.HAS_A);

            String uri1 = "/oms/edge/update";

            String inputJson = super.mapToJson(edge1);
            MvcResult mvcResult1 = mvc.perform(MockMvcRequestBuilders.post(uri1)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson)).andReturn();

            int status = mvcResult1.getResponse().getStatus();
            assertEquals(200, status);
            String content = mvcResult1.getResponse().getContentAsString();
            System.out.println(content);
        }catch (Exception e){

        }
    }

}