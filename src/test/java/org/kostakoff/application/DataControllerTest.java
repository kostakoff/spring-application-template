package org.kostakoff.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DataControllerTest {

    @Mock
    private DataRepository dataRepository;

    @InjectMocks
    private DataController dataController;

    private Data sampleData;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleData = new Data();
        sampleData.setId(1L);
        sampleData.setUrl("http://example.com");
        sampleData.setContent(Base64.getEncoder().encodeToString("sample content".getBytes()));
    }

    @Test
    void testGetAllData() {
        List<Data> dataList = Arrays.asList(sampleData);
        when(dataRepository.findAll()).thenReturn(dataList);

        List<Data> result = dataController.getAllData();

        assertEquals(1, result.size());
        verify(dataRepository, times(1)).findAll();
    }

    @Test
    void testGetDataById() {
        when(dataRepository.findById(1L)).thenReturn(Optional.of(sampleData));

        ResponseEntity<Data> response = dataController.getDataById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().getContent().length() > 0);
        verify(dataRepository, times(1)).findById(1L);
    }

    @Test
    void testGetDataById_NotFound() {
        when(dataRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Data> response = dataController.getDataById(1L);

        assertEquals(404, response.getStatusCodeValue());
        verify(dataRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateData() {
        Data newData = new Data();
        newData.setUrl("http://example.com/new");
        newData.setContent(Base64.getEncoder().encodeToString("new content".getBytes()));

        when(dataRepository.findById(1L)).thenReturn(Optional.of(sampleData));
        when(dataRepository.save(any(Data.class))).thenReturn(newData);

        ResponseEntity<Data> response = dataController.updateData(1L, newData);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("http://example.com/new", response.getBody().getUrl());
        assertTrue(response.getBody().getContent().length() > 0);
        verify(dataRepository, times(1)).findById(1L);
        verify(dataRepository, times(1)).save(any(Data.class));
    }

    @Test
    void testDeleteData() {
        when(dataRepository.existsById(1L)).thenReturn(true);

        ResponseEntity<Void> response = dataController.deleteData(1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(dataRepository, times(1)).existsById(1L);
        verify(dataRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteData_NotFound() {
        when(dataRepository.existsById(1L)).thenReturn(false);

        ResponseEntity<Void> response = dataController.deleteData(1L);

        assertEquals(404, response.getStatusCodeValue());
        verify(dataRepository, times(1)).existsById(1L);
        verify(dataRepository, times(0)).deleteById(1L);
    }
}