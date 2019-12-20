package com.hubject.iso.brocker;

import com.hubject.iso.brocker.model.SignedContratDataReference;
import com.hubject.iso.brocker.repositrory.SignedContractDataInMemoryRepository;
import com.hubject.iso.brocker.service.KeyFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class BrockerApplicationTests {

	@Autowired
	private SignedContractDataInMemoryRepository signedContractDataInMemoryRepository;

	@Test
	void keyFactory() {
		final SignedContratDataReference signedContratDataReference = new SignedContratDataReference("emaid", "pcid", "version", new Date(), "daUrl");
		KeyFactory keyFactory = new KeyFactory() {};

		String key = keyFactory.createKey(signedContratDataReference);
		assertThat(key).isEqualTo("emaid::pcid::version");

		String key2 = keyFactory.buildKey(signedContratDataReference.getEmaid(), signedContratDataReference.getPcid(), signedContratDataReference.getExiVersion());
		assertThat(key2).isEqualTo("emaid::pcid::version");
	}

	@Test
	void testInMemoryRepository(){
		final SignedContratDataReference signedContratDataReference = new SignedContratDataReference("emaid", "pcid", "version", new Date(), "url");
		signedContractDataInMemoryRepository.save(signedContratDataReference);
		Optional<SignedContratDataReference> opt = signedContractDataInMemoryRepository.find("emaid", "pcid", "version");
		assertThat(opt.isEmpty()).isFalse();
		assertThat(opt.get().getEmaid()).isEqualTo("emaid");
		assertThat(opt.get().getPcid()).isEqualTo("pcid");
		assertThat(opt.get().getExiVersion()).isEqualTo("version");

		Optional<SignedContratDataReference> optNullFind = signedContractDataInMemoryRepository.find("emaid1", "pcid", "version");
		assertThat(optNullFind.isEmpty()).isTrue();
		assertThatThrownBy(() -> optNullFind.get()).isInstanceOf(NoSuchElementException.class);

		Optional<SignedContratDataReference> optNullDelete = signedContractDataInMemoryRepository.delete("emaid1", "pcid", "version");
		assertThat(optNullDelete.isEmpty()).isTrue();

		Optional<SignedContratDataReference> optDelete = signedContractDataInMemoryRepository.delete("emaid", "pcid", "version");
		assertThat(optDelete.isEmpty()).isFalse();
		assertThat(opt.get().getEmaid()).isEqualTo("emaid");
		assertThat(opt.get().getPcid()).isEqualTo("pcid");
		assertThat(opt.get().getExiVersion()).isEqualTo("version");

		Optional<SignedContratDataReference> optNullFind1 = signedContractDataInMemoryRepository.delete("emaid", "pcid", "version");
		assertThat(optNullFind1.isEmpty()).isTrue();

		Optional<SignedContratDataReference> optNullFind3 = signedContractDataInMemoryRepository.delete(null, "pcid", "version");
	}

}
