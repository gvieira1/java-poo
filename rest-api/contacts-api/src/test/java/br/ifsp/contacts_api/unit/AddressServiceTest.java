package br.ifsp.contacts_api.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import br.ifsp.contacts_api.dto.AddressDTO;
import br.ifsp.contacts_api.exception.ResourceNotFoundException;
import br.ifsp.contacts_api.model.Address;
import br.ifsp.contacts_api.model.Contact;
import br.ifsp.contacts_api.repository.AddressRepository;
import br.ifsp.contacts_api.repository.ContactRepository;
import br.ifsp.contacts_api.service.AddressService;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

	@InjectMocks
	private AddressService addressService;

	@Mock
	private AddressRepository addressRepository;

	@Mock
	private ContactRepository contactRepository;

	@Mock
	private ModelMapper modelMapper;

	@Test
	void shouldSaveAddress_whenContactExists() {
		Long contactId = 1L;
		AddressDTO dto = new AddressDTO();
		Address address = new Address();
		Contact contact = new Contact();
		Address savedAddress = new Address();
		AddressDTO resultDTO = new AddressDTO();

		when(contactRepository.findById(contactId)).thenReturn(Optional.of(contact));
		when(addressService.convertToEntity(dto)).thenReturn(address);
		when(addressRepository.save(address)).thenReturn(savedAddress);
		when(addressService.convertToDTO(savedAddress)).thenReturn(resultDTO);

		AddressDTO result = addressService.save(contactId, dto);

		assertEquals(resultDTO, result);
		verify(contactRepository).findById(contactId);
		verify(addressRepository).save(address);
	}

	@Test
	void shouldThrowException_whenContactDoesNotExist() {
		Long contactId = 99L;
		AddressDTO dto = new AddressDTO();

		when(contactRepository.findById(contactId)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> {
			addressService.save(contactId, dto);
		});

		verify(contactRepository).findById(contactId);
		verify(addressRepository, never()).save(any());
	}

	@Test
	void shouldReturnAddresses_whenContactHasAddresses() {
		Long contactId = 1L;
		Pageable pageable = PageRequest.of(0, 10);
		Address address = new Address();
		AddressDTO dto = new AddressDTO();

		Page<Address> page = new PageImpl<>(List.of(address));

		when(addressRepository.findByContactId(contactId, pageable)).thenReturn(page);
		when(addressService.convertToDTO(address)).thenReturn(dto);

		Page<AddressDTO> result = addressService.getAddressesByContact(contactId, pageable);

		assertEquals(1, result.getContent().size());
		assertEquals(dto, result.getContent().get(0));
	}

	@Test
	void shouldThrowException_whenContactHasNoAddresses() {
		Long contactId = 1L;
		Pageable pageable = PageRequest.of(0, 10);

		when(addressRepository.findByContactId(contactId, pageable)).thenReturn(Page.empty());

		assertThrows(ResourceNotFoundException.class, () -> {
			addressService.getAddressesByContact(contactId, pageable);
		});
	}
}
