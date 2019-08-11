package com.carteira.renanvtor.conexobluetooth;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class ListaDispositivos extends ListActivity {

    BluetoothAdapter meuBluetoothAdpter2 = null;

    static  String ENDERECO_MAC = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<String> ArrayBluetooth = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        meuBluetoothAdpter2 = BluetoothAdapter.getDefaultAdapter();

        Set<BluetoothDevice> dispositivosPareados = meuBluetoothAdpter2.getBondedDevices();

        if(dispositivosPareados.size()>0){
            for(BluetoothDevice dispositivo: dispositivosPareados){
                String nomesBt = dispositivo.getName();
                String macBt = dispositivo.getAddress();
                ArrayBluetooth.add(nomesBt + "\n" + macBt);
            }
        }
        setListAdapter(ArrayBluetooth);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String informacaoGeral = ((TextView) v) . getText().toString();

        //Toast.makeText(getApplicationContext(), "Info: " + informacaoGeral, Toast.LENGTH_LONG).show();

        String enderecoMac = informacaoGeral.substring(informacaoGeral.length() - 17);

        //Toast.makeText(getApplicationContext(), "Mac: " + enderecoMac, Toast.LENGTH_LONG).show();

        Intent retornaMac = new Intent();
        retornaMac.putExtra(ENDERECO_MAC, enderecoMac);
        setResult(RESULT_OK, retornaMac);
        finish();
    }
}
