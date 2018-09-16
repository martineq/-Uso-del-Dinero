package proyectodane.usodeldinero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;

public class FinalizePurchaseActivity extends AppCompatActivity {

    /**
     * Valor total de la compra
     */
    private String st_totalPurchase;

    /**
     * Vuelto recibido de la compra, en forma de listado de String
     */
    private ArrayList<String> al_receivedChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalize_purchase);

        // Obtengo el intent que inició el activity, extraigo el listado de los billetes recibidos (cambio) y el valor de la compra
        Intent intent = getIntent();
        al_receivedChange = intent.getStringArrayListExtra(getString(R.string.received_change));
        st_totalPurchase = intent.getStringExtra(getString(R.string.tag_total_value));

        // Si no recibí vuelto dejo inhabilitado el botón de "Guardar vuelto"
        if (al_receivedChange.isEmpty()) {
            Button saveToWalletButton = (Button) findViewById(R.id.button13);
            saveToWalletButton.setEnabled(false);
        }

    }

    @Override
    public void onBackPressed() {
        // No realiza acción, para obligar al usuario a tomar la decisión a través de los botones
    }

    /**
     * Envía a la pantalla principal
     * */
    public void sendToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Concreta el pago, descartando los valores correspondientes de la billetera y regresa a la pantalla principal
     * */
    public void doNotSaveToWallet(View view) {
        // TODO: Aquí se debe concretar el pago (Quitar los valores de la billetera desde el WalletManager)
        sendToMain(view);
    }

    /**
     * Concreta el pago, descartando los valores correspondientes de la billetera,
     * guarda todos los valores recibidos en concepto de vuelto y regresa a la pantalla principal
     * */
    public void saveToWallet(View view) {
        // TODO: Aquí se debe concretar el pago (Quitar los valores de la billetera desde el WalletManager)
        // TODO: Aquí se deben guardar todos los billetes recibidos (vuelto)
        sendToMain(view);
    }

}
