package br.inatel.pos.dm111.vfp.consumer;

import java.util.List;

public record UserEvent(String id, String name, String email, String password, String type, List<String> preferredCategories) {
}
