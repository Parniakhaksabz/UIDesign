package com.example.myapplication;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.myapp.databinding.FragmentExampleBinding;

public class ExampleFragment extends Fragment {

    private FragmentExampleBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate using ViewBinding
        binding = FragmentExampleBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Set text in the TextView
        binding.textViewFragment.setText("متن جدید فرگمنت");

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

