import dto.AddInvoiceRequest;
import dto.InvoiceDto;
import dto.LineItemDto;
import endpoint.InvoiceController;
import model.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import repo.InvoiceRepository;
import testutils.InvoiceManagerTestUtility;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class InvoiceControllerTest {
    @Mock
    private InvoiceRepository invoiceRepository;
    @InjectMocks
    private InvoiceController invoiceController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(invoiceController).build();
    }

    @Test
    public void test_viewInvoice() throws Exception {
        when(invoiceRepository.getOne(anyLong()))
                .thenReturn(InvoiceManagerTestUtility.jsonFileToObject("invoiceDto.json", Invoice.class));

        MvcResult result = mockMvc.perform(get("/invoices/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        InvoiceDto invoiceDto = InvoiceManagerTestUtility.jsonStringToObject(jsonResponse, InvoiceDto.class);
        assertNotNull(invoiceDto);
    }

    /*@Test
    public void skfjaesf() throws Exception {
        AddInvoiceRequest addInvoiceRequest = new AddInvoiceRequest();
        addInvoiceRequest.setClientName("Fako");
        addInvoiceRequest.setInvoiceDate(new Date());
        addInvoiceRequest.setVatRate(14L);
        for(int i =0; i < 5; i++){
            addInvoiceRequest.getLineItemDtos().add(new LineItemDto(1L, "Fako", new BigDecimal(14.5)));
        }
        //addInvoiceRequest.setLineItemDtos(Arrays.asList(new LineItemDto(1L, "Fako", new BigDecimal(14.5)), new LineItemDto(), new LineItemDto()));

        String json = InvoiceManagerTestUtility.objectToJsonString(addInvoiceRequest);
        System.out.println(json);
    }*/
}
