/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prova.Threads;

import com.mycompany.prova.JServer;

/**
 *
 * @author peduzzi_samuele
 */
public class JServerTh extends Thread {

    private JServer server;

    public JServerTh() {
        server = new JServer();
    }

    @Override
    public void run() {
        server.execServerRoutine();
    }

}
