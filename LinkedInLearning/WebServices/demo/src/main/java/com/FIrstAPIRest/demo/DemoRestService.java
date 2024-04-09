package com.FIrstAPIRest.demo;

import com.FIrstAPIRest.model.Equipo;
import com.FIrstAPIRest.model.Liga;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@Tag(name="DemoRestService", description = "API de la liga")
public class DemoRestService {
  @Autowired
  private Liga liga;

  public @ResponseBody ResponseEntity<Collection<Equipo>> getEquipos() {

  }

  public @ResponseBody ResponseEntity<Equipo> getEquipo {
    
  }

}
