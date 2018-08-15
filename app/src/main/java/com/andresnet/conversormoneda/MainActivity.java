package com.andresnet.conversormoneda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

       Spinner opciones, opciones2;
       EditText cant;
       TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         opciones = (Spinner) findViewById(R.id.spinner);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.opciones, android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);

       opciones2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.opciones2, android.R.layout.simple_spinner_item);
        opciones2.setAdapter(adapter2);




    }
     public void Convertir(View view){
         cant = (EditText)findViewById(R.id.editText);
         result = (TextView)findViewById(R.id.tResult);
         opciones = (Spinner) findViewById(R.id.spinner);
         opciones2 = (Spinner) findViewById(R.id.spinner2);
         if (cant.getText().toString().trim().equalsIgnoreCase("") ){
             Toast.makeText(MainActivity.this,"No se ha indicado la cantidad", Toast.LENGTH_SHORT).show();

         }else {
             String monedaActual = opciones.getSelectedItem().toString();
             String monedaCambio = opciones2.getSelectedItem().toString();

             double valorCambio = Double.parseDouble(cant.getText().toString());
             double resulta = conversion(monedaActual, monedaCambio, valorCambio);
             if (resulta > 0) {
                 result.setText(String.format("Por %5.2f %s usted recibira %5.2f %s", valorCambio, monedaActual, resulta, monedaCambio));
                 cant.setText("");
             } else {
                 result.setText(String.format("Usted recibira"));
                 Toast.makeText(MainActivity.this, "Las opciones elegidas no tienen un factor de conversión", Toast.LENGTH_SHORT).show();
             }

         }
     }
     private double conversion(String monedaActual, String monedaCambio, double valorCambio){
        double resultConversion = 0;

        switch (monedaActual){
            case "Dollar":
                if (monedaCambio.equals("Euros"))
                    resultConversion = valorCambio*0.88;

                if (monedaCambio.equals("Pesos"))
                    resultConversion = valorCambio*2982.35;

                break;
            case "Pesos":
                if (monedaCambio.equals("Dollar"))
                    resultConversion = valorCambio*0.00034;

                if (monedaCambio.equals("Euros")){
                    resultConversion = valorCambio*0.00030;
                }
                break;
            case "Euros":
                if (monedaCambio.equals("Dollar"))
                    resultConversion = valorCambio*1.14;

                if (monedaCambio.equals("Pesos"))
                    resultConversion = valorCambio*3401.36;

                break;
        }

        return resultConversion ;
     }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
