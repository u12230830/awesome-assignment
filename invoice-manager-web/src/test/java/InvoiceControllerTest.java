import dto.InvoiceDto;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
        verify(invoiceRepository, times(1)).getOne(anyLong());
        assertEquals(BigDecimal.valueOf(105.50).setScale(2, BigDecimal.ROUND_HALF_UP), invoiceDto.getSubTotal());
        assertEquals(BigDecimal.valueOf(14.77), invoiceDto.getVat());
        assertEquals(BigDecimal.valueOf(120.27), invoiceDto.getTotal());
    }
}
