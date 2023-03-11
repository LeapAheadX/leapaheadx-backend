package com.oop.leap_ahead_x.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.UUID;


public class OptionsDTO {

    private UUID optionUuid;

    @NotNull
    @Size(max = 255)
    private String optionPrompt;

    private List<UUID> optionComponentLinkInputComponents;

    public UUID getOptionUuid() {
        return optionUuid;
    }

    public void setOptionUuid(final UUID optionUuid) {
        this.optionUuid = optionUuid;
    }

    public String getOptionPrompt() {
        return optionPrompt;
    }

    public void setOptionPrompt(final String optionPrompt) {
        this.optionPrompt = optionPrompt;
    }

    public List<UUID> getOptionComponentLinkInputComponents() {
        return optionComponentLinkInputComponents;
    }

    public void setOptionComponentLinkInputComponents(
            final List<UUID> optionComponentLinkInputComponents) {
        this.optionComponentLinkInputComponents = optionComponentLinkInputComponents;
    }

}
