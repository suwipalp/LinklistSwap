package com.examination.linklistswap.service;

import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LinklistSwapService {

    public String swapLinklist(String linklist, String linklistSplit) {
        // base64 decode
        byte[] decodedBytes = Base64.getDecoder().decode(linklist);
        String decodedString = new String(decodedBytes);

        // swap
        List<String> items = Arrays.asList(decodedString.split(linklistSplit));
        for (int i = 0; i + 1 < items.size(); i += 2) {
            Collections.swap(items, i, i + 1);
        }
        String swappedItems = String.join(linklistSplit, items);

        // base64 encode
        String encodedString = Base64.getEncoder().encodeToString(swappedItems.getBytes());
        return encodedString;
    }
}
