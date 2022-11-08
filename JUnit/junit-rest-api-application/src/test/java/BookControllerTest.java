/* Mockito -> mockito  is popular open source framework for mocking objects   By using this we can simplefy the development test for the classes

*   A mock object is an dummy implementation for an interface or class  (bookrepository) they typically interact with the system to test the applicaiton  */

import com.example.junitrestapiapplication.Model.BookEntity;
import com.example.junitrestapiapplication.Repository.BookRepository;
import com.example.junitrestapiapplication.com.rest.api.BookController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.client.match.JsonPathRequestMatchers;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter =objectMapper.writer();

    @Mock
    private BookRepository bookRepository;

    @InjectMocks

    private BookController bookController;


    BookEntity record_1 = new BookEntity(1L,"ABC","How to build using Junti",3);
    BookEntity record_2 = new BookEntity(2L,"XYZ","Using Mockito",3);
    BookEntity record_3 = new BookEntity(3L,"DEF","Buidling our application",4);

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void getAllRecords_Success() throws Exception{
        List<BookEntity> records = new ArrayList<>(Arrays.asList(record_1,record_2,record_3));

        Mockito.when(bookRepository.findAll()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/book")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)));
    }

    @Test
    public void getBookbyId_success() throws Exception{
        Mockito.when(bookRepository.findById(record_1.getBookId())).thenReturn(java.util.Optional.of(record_1));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/book/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()));
    }

    @Test
    public void createRecord_success() throws Exception{
        BookEntity record = BookEntity.builder()
                .bookId(4L)
                .bookName("Dev")
                .summary("Inserting the data through Mockito")
                .rating(5)
                .build();

        Mockito.when(bookRepository.save(record)).thenReturn(record);

        String content  = objectWriter.writeValueAsString(record);

        MockHttpServletRequestBuilder MockRequest = MockMvcRequestBuilders.post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(MockRequest)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",notNullValue()));

    }

    @Test
    public void updateBookRecordId_success() throws Exception{
        BookEntity updatedRecord = BookEntity.builder()
                .bookId(1L)
                .bookName("Updated the Name of Book")
                .summary("Updated")
                .rating(3)
                .build();

        Mockito.when(bookRepository.findById(record_1.getBookId())).thenReturn(java.util.Optional.ofNullable(record_1));
        Mockito.when(bookRepository.save(updatedRecord)).thenReturn(updatedRecord);

        String updateContect = objectWriter.writeValueAsString(updatedRecord);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(updateContect);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",notNullValue()));
    }

    @Test
    public void deleteBookById_success() throws Exception{
        Mockito.when(bookRepository.findById(record_2.getBookId())).thenReturn(Optional.of(record_2));

        mockMvc.perform(MockMvcRequestBuilders.delete("/book/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
