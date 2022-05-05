package com.ofrs.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.mockito.Mockito;
import com.ofrs.model.Complain;
import com.ofrs.repository.ComplainRepository;
import com.sun.source.tree.ModuleTree.ModuleKind;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ComplainServiceTest {
	
	@Autowired
	private ComplainService complainService;
	
	@MockBean
	private ComplainRepository complainRepository;

	@Test
	public void testGetAllComplain() {
		Complain complain1 = new Complain("Website lagging", "Open");
		Complain complain2 = new Complain("Network issue", "Open");
		
		List<Complain> complain = new ArrayList<>();
		complain.add(complain1);
		complain.add(complain2);
		
		Mockito.when(complainService.getAllComplain()).thenReturn(complain);
		assertThat(complainService.getAllComplain()).isEqualTo(complain);
	}

	@Test
	public void testAddComplain() {
		Complain complain = new Complain("Website lagging", "Open");
		Mockito.when(complainRepository.save(complain)).thenReturn(complain);
		assertThat(complain.getComplainId() == 1);
	}


	@Test
	public void testDeleteComplainById() {
		Complain complain = new Complain("Website lagging", "Open");
		
		assertThat(complain.getComplainId() > 0);
		Mockito.when(complainRepository.getById(1)).thenReturn(complain);
	}

	@Test
	public void testUpdateComplainById() {
		Complain complain = new Complain("Website lagging", "Open");
		assertThat(complain.getComplainId()>0);
		Mockito.when(complainRepository.save(complain)).thenReturn(complain);
		assertThat(complainService.updateComplainById(2, complain)).isEqualTo(complain);
	}

}
