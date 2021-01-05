package com.CSI5324;

import com.CSI5324.model.Node;
import com.CSI5324.model.NodeCategory;
import com.CSI5324.model.Person;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.sql.Date;

import static org.junit.Assert.assertEquals;

public class PersonControllerTests extends AbstractTest{
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }



    @Test
    public void createPersonTest() throws Exception {
        try {
            Person testPerson = createPerson("test person", "1234567890", Date.valueOf("2020-12-12"), "abc@gmail.com", "test12345");
            String uri = "/oms/person/register";
            String inputJson = super.mapToJson(testPerson);
            MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson)).andReturn();

            int status = mvcResult.getResponse().getStatus();
            assertEquals(200, status);
            String content = mvcResult.getResponse().getContentAsString();
            System.out.println(content);
        }catch (Exception e){

        }
    }

    @Test
    public void deletePersonTest() throws Exception {
        try {
            String uri = "/oms/person/deleteById?id=2";
            MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
            int status = mvcResult.getResponse().getStatus();
            assertEquals(200, status);
            String content = mvcResult.getResponse().getContentAsString();
            System.out.println(content);
        }catch(Exception e){

        }
    }

    @Test
    public void updatePersonTest() throws Exception {

        try {
            String uri = "/oms/person/findById?id=3";
            MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
            System.out.println(mvcResult.getResponse().getContentAsString());
            Person person1 = super.mapFromJson(mvcResult.getResponse().getContentAsString(), Person.class);
            if (person1 != null) {

                person1.setEmail("xyz@email.com");
                String uri1 = "/oms/person/update";

                String inputJson = super.mapToJson(person1);
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
