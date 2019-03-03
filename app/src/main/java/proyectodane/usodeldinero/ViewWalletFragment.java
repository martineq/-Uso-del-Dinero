package proyectodane.usodeldinero;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class ViewWalletFragment extends Fragment {

    /**
     * Vista instanciada
     */
    View rootView;

    public ViewWalletFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_view_wallet, container, false);
        return rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Verifico inicialización de archivo de valores
        WalletManager.getInstance().checkFirstRun(getActivity());

        // Calculo todos los valores en la billetera a mostrar
        ArrayList<String> moneyValueNames = WalletManager.getInstance().obtainMoneyValueNamesInWallet(getActivity());

        // Actualizo el valor del total en billetera
        refreshTotal(WalletManager.getInstance().obtainTotalCreditInWallet(getActivity()));

        // Clase que se encarga de manejar lo referido al slide de imágenes y puntos
        // Parámetros:  + (1)Contexto
        //              + (2)ViewPager con su (3)FragmentManager y sus (4)moneyValueNames (nombres de las imágenes)
        //              + (5)LinearLayout y sus (6)(7)imágenes representando al punto
        ImageSlideManager imageSlideManager = new ImageSlideManager(getActivity(),
                (ViewPager) rootView.findViewById(R.id.pager_main),
                getActivity().getSupportFragmentManager(),
                moneyValueNames,
                (LinearLayout) rootView.findViewById(R.id.SliderDots_main),
                ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.active_dot),
                ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.nonactive_dot));

    }


    /**
     * Envía a la pantalla de ingreso de importe de productos al presionar el botón
     **/
    public void sendToBasket(View view) {
        Intent intent = new Intent(getActivity(), BasketActivity.class);
        startActivity(intent);
    }

    /**
     * Envía a la pantalla de carga de billetera
     **/
    public void sendToLoadWallet(View view) {
        //Intent intent = new Intent(this, WalletActivity.class); // Todo: Resolver llamada
        Intent intent = new Intent(getActivity(), MainTabActivity.class);
        startActivity(intent);
    }

    /**
     * Muestra el texto de ayuda para este activity
     **/
    public void showHelp(View view) {
        SnackBarManager sb = new SnackBarManager();
        sb.showTextIndefiniteOnClickActionDisabled(rootView.findViewById(R.id.coordinatorLayout_Main),getString(R.string.help_text_main),7);
    }

    /**
     * Actualiza el valor de la carga y del total
     **/
    public void refreshTotal(String newTotal) {
        TextView textView = rootView.findViewById(R.id.textView1);
        String newText = getString(R.string.saved_money_pesos) + newTotal;
        textView.setText(newText);
    }


}