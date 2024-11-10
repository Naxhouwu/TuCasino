package com.example.menucasino;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout numeroBoletaLayout;
    private TextInputEditText numeroBoletaInput;
    private MaterialButton valorarButton;
    private CardView menuCard1;
    private CardView menuCard2;
    private CardView menuCard3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        initViews();

        // Configurar listeners
        setupListeners();
    }

    protected void onResume() {
        super.onResume();
        // Asegurar que el EditText no tome el foco al volver a la actividad
        View rootView = findViewById(android.R.id.content);
        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
    }

    private void initViews() {
        numeroBoletaLayout = findViewById(R.id.numeroBoletaLayout);
        numeroBoletaInput = findViewById(R.id.numeroBoletaInput);
        valorarButton = findViewById(R.id.botonValorar);
        menuCard1 = findViewById(R.id.menuCard1);
        menuCard2 = findViewById(R.id.menuCard2);
        menuCard3 = findViewById(R.id.menuCard3);
    }

    private void setupListeners() {
        valorarButton.setOnClickListener(v -> validarYNavigar());
    }

    private void validarYNavigar() {
        String numeroBoleta = numeroBoletaInput.getText().toString();

        if (numeroBoleta.isEmpty()) {
            numeroBoletaLayout.setError("Por favor ingrese el número de boleta");
            return;
        }

        // Limpiar error si existe
        numeroBoletaLayout.setError(null);

        // Limpiar el campo de texto
        numeroBoletaInput.setText("");

        // Mostrar mensaje de confirmación
        Toast.makeText(this, "Número de boleta: " + numeroBoleta, Toast.LENGTH_SHORT).show();

        // Navegar a la actividad de valoración
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("numeroBoleta", numeroBoleta);
        startActivity(intent);
    }
}