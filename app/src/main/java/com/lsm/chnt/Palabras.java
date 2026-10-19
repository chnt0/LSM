package com.lsm.chnt;

public class Palabras {
    private String palabra;
    private String video;

    public Palabras(String palabra, String video) {
        this.palabra = palabra;
        this.video = video;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
