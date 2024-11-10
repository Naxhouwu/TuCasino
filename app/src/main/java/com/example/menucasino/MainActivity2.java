package com.example.menucasino;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity2 extends AppCompatActivity {
    // Declaración de variables de clase
    private RatingBar ratingAtencion;
    private RatingBar ratingSabor;
    private RatingBar ratingPresentacion;
    private RatingBar ratingTiempoEspera;
    private TextInputEditText sugerenciasInput;
    private MaterialButton enviarButton;
    private String numeroBoleta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Obtener número de boleta del intent
        numeroBoleta = getIntent().getStringExtra("numeroBoleta");

        // Inicializar vistas
        initViews();

        // Configurar listeners
        setupListeners();
    }

    private void initViews() {
        ratingAtencion = findViewById(R.id.ratingAtencion);
        ratingSabor = findViewById(R.id.ratingSabor);
        ratingPresentacion = findViewById(R.id.ratingPresentacion);
        ratingTiempoEspera = findViewById(R.id.ratingTiempoEspera);
        sugerenciasInput = findViewById(R.id.sugerenciasInput);
        enviarButton = findViewById(R.id.button2);
    }

    private void setupListeners() {
        enviarButton.setOnClickListener(v -> enviarValoracion());

        RatingBar.OnRatingBarChangeListener ratingListener = (ratingBar, rating, fromUser) -> {
            if (fromUser) {
                actualizarEtiquetaRating(ratingBar, rating);
            }
        };

        ratingAtencion.setOnRatingBarChangeListener(ratingListener);
        ratingSabor.setOnRatingBarChangeListener(ratingListener);
        ratingPresentacion.setOnRatingBarChangeListener(ratingListener);
        ratingTiempoEspera.setOnRatingBarChangeListener(ratingListener);
    }

    private void actualizarEtiquetaRating(RatingBar ratingBar, float rating) {
        String mensaje = String.format("Calificación: %.1f", rating);
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private void enviarValoracion() {
        ValoracionData valoracion = new ValoracionData(
                numeroBoleta,
                ratingAtencion.getRating(),
                ratingSabor.getRating(),
                ratingPresentacion.getRating(),
                ratingTiempoEspera.getRating(),
                sugerenciasInput.getText().toString()
        );

        if (validarValoraciones(valoracion)) {
            Toast.makeText(this, "¡Gracias por tu valoración!", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private boolean validarValoraciones(ValoracionData valoracion) {
        if (valoracion.getAtencion() == 0 ||
                valoracion.getSabor() == 0 ||
                valoracion.getPresentacion() == 0 ||
                valoracion.getTiempoEspera() == 0) {
            Toast.makeText(this, "Por favor completa todas las calificaciones", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}

// Clase auxiliar para manejar los datos
class ValoracionData {
    private String numeroBoleta;
    private float atencion;
    private float sabor;
    private float presentacion;
    private float tiempoEspera;
    private String sugerencias;

    public ValoracionData(String numeroBoleta, float atencion, float sabor,
                          float presentacion, float tiempoEspera, String sugerencias) {
        this.numeroBoleta = numeroBoleta;
        this.atencion = atencion;
        this.sabor = sabor;
        this.presentacion = presentacion;
        this.tiempoEspera = tiempoEspera;
        this.sugerencias = sugerencias;
    }

    public float getAtencion() { return atencion; }
    public float getSabor() { return sabor; }
    public float getPresentacion() { return presentacion; }
    public float getTiempoEspera() { return tiempoEspera; }
}
