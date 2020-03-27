package com.examination.linklistswap.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.examination.linklistswap.service.LinklistSwapService;
import com.examination.linklistswap.validator.LinklistConstraint;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class LinklistSwapController {

    private static final String LINKLIST_SPLIT = "->";
    
    private LinklistSwapService swapService;

    public LinklistSwapController() {
        swapService = new LinklistSwapService();
    }

    @GetMapping("/linklistswap")
    public String linklistSwap(
        @RequestParam(value = "linklist", required = false)
        @Valid
        @NotEmpty
        @LinklistConstraint(
            regexp = "^((\\d+)" + LINKLIST_SPLIT + ")+(\\d+)$",
            message = "Input format is invalid (must be digit linked with '" + LINKLIST_SPLIT + "', e.g. 1" + LINKLIST_SPLIT + "2, 1" + LINKLIST_SPLIT + "2" + LINKLIST_SPLIT + "3)")
        String linklist) throws Exception {

        String encodedString = swapService.swapLinklist(linklist, LINKLIST_SPLIT);

        return encodedString;
    }
}
