package com.warnermedia.wdm.remove;

public class LinuxRemover extends AbstractRemover implements Remover {

    @Override
    public void remove(String filePath, String[] fileName) throws Exception {
        try {
            String[] command = {"bash", "-c", "rm "};
            for(String file : fileName) {
                removeAFile(filePath, file, command);
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
