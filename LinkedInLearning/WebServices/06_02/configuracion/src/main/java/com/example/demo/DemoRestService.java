package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Liga;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name="DemoRestService", description="API de la Liga")
public class DemoRestService {
	@Autowired
	private Liga liga;
}
