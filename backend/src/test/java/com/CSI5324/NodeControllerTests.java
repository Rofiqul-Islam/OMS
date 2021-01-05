package com.CSI5324;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.CSI5324.model.Node;
import com.CSI5324.model.NodeCategory;
import com.CSI5324.model.OtherProperties;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;


public class NodeControllerTests extends AbstractTest {
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}


	@Test
	public void createNodeTest() throws Exception {
		String uri = "/oms/node/create";
		Node node1 = createNode("node1", NodeCategory.BuildingComponent,null);
		String inputJson = super.mapToJson(node1);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println(content);
	}

	@Test
	public void deleteNodeTest() throws Exception {
		String uri = "/oms/node/deleteById?id=8";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println(content);
	}

	@Test
	public void updateNodeTest() throws Exception {

		try {
			String uri = "/oms/node/findById?id=1";
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
			System.out.println(mvcResult.getResponse().getContentAsString());
			Node node1 = super.mapFromJson(mvcResult.getResponse().getContentAsString(), Node.class);
			if (node1 != null) {

				node1.setNodeCategory(NodeCategory.HealthConcern);

				String uri1 = "/oms/node/update";

				String inputJson = super.mapToJson(node1);
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