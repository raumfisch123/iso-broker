package com.hubject.iso.brocker;

import com.hubject.iso.brocker.model.SignedContratDataReference;
import com.hubject.iso.brocker.repositrory.SignedContractDataInMemoryRepository;
import com.hubject.iso.brocker.service.KeyFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class BrockerApplicationTests {

    @Autowired
    private SignedContractDataInMemoryRepository signedContractDataInMemoryRepository;

    @Test
    void keyFactory() {
        final SignedContratDataReference signedContratDataReference = new SignedContratDataReference(
                "alias", "emaid", "pcid", "version", new Date(), "daUrl");
        KeyFactory keyFactory = new KeyFactory();

        String key = keyFactory.createKey(signedContratDataReference);
        assertThat(key).isEqualTo("alias::emaid::pcid::version");

        String key2 = keyFactory.buildKey(signedContratDataReference.getAlias(), signedContratDataReference.getEmaid(),
                signedContratDataReference.getPcid(), signedContratDataReference.getExiVersion());
        assertThat(key2).isEqualTo("alias::emaid::pcid::version");
    }

    @Test
    void testInMemoryRepository() {
        final SignedContratDataReference signedContratDataReference =
                new SignedContratDataReference("alias", "emaid", "pcid", "version", new Date(), "url");
        signedContractDataInMemoryRepository.save(signedContratDataReference);
        Optional<SignedContratDataReference> opt = signedContractDataInMemoryRepository
                .find("alias", "emaid", "pcid", "version");
        assertThat(opt.isEmpty()).isFalse();
        assertThat(opt.get().getAlias()).isEqualTo("alias");
        assertThat(opt.get().getEmaid()).isEqualTo("emaid");
        assertThat(opt.get().getPcid()).isEqualTo("pcid");
        assertThat(opt.get().getExiVersion()).isEqualTo("version");

        Optional<SignedContratDataReference> optNullFind = signedContractDataInMemoryRepository
                .find("alias", "emaid1", "pcid", "version");
        assertThat(optNullFind.isEmpty()).isTrue();
        assertThatThrownBy(() -> optNullFind.get()).isInstanceOf(NoSuchElementException.class);

        Optional<SignedContratDataReference> optNullDelete = signedContractDataInMemoryRepository
                .delete("alias", "emaid1", "pcid", "version");
        assertThat(optNullDelete.isEmpty()).isTrue();

        Optional<SignedContratDataReference> optDelete = signedContractDataInMemoryRepository
                .delete("alias", "emaid", "pcid", "version");
        assertThat(optDelete.isEmpty()).isFalse();
        assertThat(optDelete.get().getAlias()).isEqualTo("alias");
        assertThat(optDelete.get().getEmaid()).isEqualTo("emaid");
        assertThat(optDelete.get().getPcid()).isEqualTo("pcid");
        assertThat(optDelete.get().getExiVersion()).isEqualTo("version");

        Optional<SignedContratDataReference> optNullFind1 = signedContractDataInMemoryRepository
                .delete("alias", "emaid", "pcid", "version");
        assertThat(optNullFind1.isEmpty()).isTrue();

        Optional<SignedContratDataReference> optNullFind3 = signedContractDataInMemoryRepository
                .delete("alias", null, "pcid", "version");

        signedContractDataInMemoryRepository.save(
                new SignedContratDataReference("alias1", "emaid1", "pcid1", "version", new Date(), "blubb1"));
        signedContractDataInMemoryRepository.save(
                new SignedContratDataReference("alias1", "emaid1", "pcid2", "version", new Date(), "blubb2"));
        signedContractDataInMemoryRepository.save(
                new SignedContratDataReference("alias1", "emaid1", "pcid3", "version", new Date(), "blubb3"));

        List<SignedContratDataReference> findAllForEmaid =
                signedContractDataInMemoryRepository.find("alias", "emaid");
        assertThat(findAllForEmaid.size()).isEqualTo(0);

        List<SignedContratDataReference> findAllForEmaid2 =
                signedContractDataInMemoryRepository.find("alias1", "emaid1");
        assertThat(findAllForEmaid2.size()).isEqualTo(3);

    }

}
