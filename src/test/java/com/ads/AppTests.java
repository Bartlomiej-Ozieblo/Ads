package com.ads;

import com.ads.controller.*;
import com.ads.domain.*;
import com.ads.repository.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml", "file:src/main/webapp/WEB-INF/spring-security.xml"})
public class AppTests {

    private MockMvc mockMvc;

    @Mock
    private UserDAO userRepository;

    @Mock
    private CategoryDAO categoryRepository;

    @Mock
    private AdDAO adRepository;

    @Mock
    private RoleDAO roleRepository;

    @Mock
    private ContactDAO contactRepository;

    @InjectMocks
    private CategoryController categoryController;

    @InjectMocks
    private AdminController adminController;

    @InjectMocks
    private AdsController adsController;

    @InjectMocks
    private ErrorHandlerController errorHandlerController;

    @InjectMocks
    private RegisterController registerController;

    @InjectMocks
    private UserController userController;


    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(categoryController,
                adminController, adsController, errorHandlerController,
                registerController, userController).build();
    }

    @Test
    public void categoryControllerTest() throws Exception {
        this.mockMvc.perform(post("/")).andExpect(status().isOk());
        this.mockMvc.perform(post("/admin/categories")).andExpect(status().isOk());
        this.mockMvc.perform(post("/admin/category/add")).andExpect(redirectedUrl("/admin/categories"));
        this.mockMvc.perform(post("/admin/category/id/1/remove")).andExpect(redirectedUrl("/admin/categories"));
    }

    @Test
    public void adminURLTest() throws Exception {
        this.mockMvc.perform(post("/admin")).andExpect(redirectedUrl("/admin/ads"));
        this.mockMvc.perform(post("/admin/users")).andExpect(status().isOk());
    }

    @Test
    public void categoryDAOTest() throws Exception {
        Category category = new Category();
        category.setCategoryName("TEST");
        this.categoryRepository.save(category);
        for (Category tmp : this.categoryRepository.findAll()) {
            if (tmp.getCategoryName().equals("TEST")) {
                Assert.assertTrue(true);
            }
        }
    }

    @Test
    public void adDAOTest() throws Exception {
        Ad ad = new Ad();
        ad.setTitle("TEST");
        this.adRepository.save(ad);
        for (Ad tmp : this.adRepository.findAll()) {
            if (tmp.getTitle().equals("TEST")) {
                Assert.assertTrue(true);
            }
        }
    }

    @Test
    public void contactDAOTest() throws Exception {
        Contact contact = new Contact();
        contact.setEmail("TEST");
        this.contactRepository.save(contact);
        for (Contact tmp : this.contactRepository.findAll()) {
            if (tmp.getEmail().equals("TEST")) {
                Assert.assertTrue(true);
            }
        }
    }

    @Test
    public void userDAOTest() throws Exception {
        User user = new User();
        user.setUserName("TEST");
        this.userRepository.save(user);
        for (User tmp : this.userRepository.findAll()) {
            if (tmp.getUserName().equals("TEST")) {
                Assert.assertTrue(true);
            }
        }
    }

    @Test
    public void roleDAOTest() throws Exception {
        Role adminRole = new Role();
        adminRole.setRoleName("ROLE_ADMIN");

        this.roleRepository.save(adminRole);
        for (Role role : this.roleRepository.findAll()) {
            if (role.getRoleName().equals("ROLE_ADMIN")) {
                Assert.assertTrue(true);
            }
        }
    }
}
