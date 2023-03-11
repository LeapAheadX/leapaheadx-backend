package com.oop.leap_ahead_x.service;

import com.oop.leap_ahead_x.domain.InputComponent;
import com.oop.leap_ahead_x.domain.Options;
import com.oop.leap_ahead_x.dto.OptionsDTO;
import com.oop.leap_ahead_x.repos.InputComponentRepository;
import com.oop.leap_ahead_x.repos.OptionsRepository;
import com.oop.leap_ahead_x.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Transactional
@Service
public class OptionsService {

    private final OptionsRepository optionsRepository;
    private final InputComponentRepository inputComponentRepository;

    public OptionsService(final OptionsRepository optionsRepository,
            final InputComponentRepository inputComponentRepository) {
        this.optionsRepository = optionsRepository;
        this.inputComponentRepository = inputComponentRepository;
    }

    public List<OptionsDTO> findAll() {
        final List<Options> optionss = optionsRepository.findAll(Sort.by("optionUuid"));
        return optionss.stream()
                .map((options) -> mapToDTO(options, new OptionsDTO()))
                .toList();
    }

    public OptionsDTO get(final UUID optionUuid) {
        return optionsRepository.findById(optionUuid)
                .map(options -> mapToDTO(options, new OptionsDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final OptionsDTO optionsDTO) {
        final Options options = new Options();
        mapToEntity(optionsDTO, options);
        return optionsRepository.save(options).getOptionUuid();
    }

    public void update(final UUID optionUuid, final OptionsDTO optionsDTO) {
        final Options options = optionsRepository.findById(optionUuid)
                .orElseThrow(NotFoundException::new);
        mapToEntity(optionsDTO, options);
        optionsRepository.save(options);
    }

    public void delete(final UUID optionUuid) {
        optionsRepository.deleteById(optionUuid);
    }

    private OptionsDTO mapToDTO(final Options options, final OptionsDTO optionsDTO) {
        optionsDTO.setOptionUuid(options.getOptionUuid());
        optionsDTO.setOptionPrompt(options.getOptionPrompt());
        optionsDTO.setOptionComponentLinkInputComponents(options.getOptionComponentLinkInputComponents() == null ? null : options.getOptionComponentLinkInputComponents().stream()
                .map(inputComponent -> inputComponent.getComponentUuid())
                .toList());
        return optionsDTO;
    }

    private Options mapToEntity(final OptionsDTO optionsDTO, final Options options) {
        options.setOptionPrompt(optionsDTO.getOptionPrompt());
        final List<InputComponent> optionComponentLinkInputComponents = inputComponentRepository.findAllById(
                optionsDTO.getOptionComponentLinkInputComponents() == null ? Collections.emptyList() : optionsDTO.getOptionComponentLinkInputComponents());
        if (optionComponentLinkInputComponents.size() != (optionsDTO.getOptionComponentLinkInputComponents() == null ? 0 : optionsDTO.getOptionComponentLinkInputComponents().size())) {
            throw new NotFoundException("one of optionComponentLinkInputComponents not found");
        }
        options.setOptionComponentLinkInputComponents(optionComponentLinkInputComponents.stream().collect(Collectors.toSet()));
        return options;
    }

}
