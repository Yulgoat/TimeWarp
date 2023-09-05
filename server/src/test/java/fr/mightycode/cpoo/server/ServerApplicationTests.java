package fr.mightycode.cpoo.server;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ServerApplicationTests {

  @Autowired
  private MockMvc mvc;

  @Test
  void testSignUpSignIn() throws Exception {
    mvc.perform(post("/user/signup")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content("""
          {
            "login": "admin",
            "password": "admin"
          }"""))
      .andExpect(status().isBadRequest());
    mvc.perform(post("/user/signup")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content("""
          {
            "login": "test",
            "password": "test"
          }"""))
      .andExpect(status().isOk());
    mvc.perform(post("/user/signup")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content("""
          {
            "login": "test",
            "password": "test"
          }"""))
      .andExpect(status().isBadRequest());
    mvc.perform(post("/user/signin")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content("""
          {
            "login": "test",
            "password": "test"
          }"""))
      .andExpect(status().isOk());
  }
}
