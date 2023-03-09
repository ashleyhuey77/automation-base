package com.wdm.remove;

public class RemoveController implements Remover {

    private final Remover remover;

    public RemoveController(Remover remover) {
        this.remover = remover;
    }

    @Override
    public void remove(String filePath, String[] fileName) throws Exception {
        remover.remove(filePath, fileName);
    }
}
