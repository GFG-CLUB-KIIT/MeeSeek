package co.kit.gfg.chatapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;


import org.jetbrains.annotations.NotNull;

public class UpdatePasswordDialog extends AppCompatDialogFragment {
    EditText currentPassword;
    EditText newPassword;
    DatabaseHelper databaseHelper;
    @NotNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder((getActivity()));
        LayoutInflater inflater=getActivity().getLayoutInflater();

        View view=inflater.inflate(R.layout.update_password,null);

        builder.setView(view)
                .setTitle("Update Password")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        currentPassword=view.findViewById(R.id.currentPassword);
                        newPassword=view.findViewById(R.id.newPassword);
                        String CurrentPassword=currentPassword.getText().toString();
                        String NewPassword=newPassword.getText().toString();

                        update(CurrentPassword,NewPassword);

                    }
                });

        return builder.create();
    }


    private void update(String Currentpassword,String NewPassword) {
        if(Currentpassword.equals(UserInfo.CurrentPassword(requireContext())))
        {
            UserInfo.UpdatePassword(requireContext(), NewPassword);

            databaseHelper=new DatabaseHelper(requireContext());
            String BluetoothName=UserInfo.BluetoothName(requireContext());
            String Username=UserInfo.Username(requireContext());
            databaseHelper.deleteRow(BluetoothName,Username,Currentpassword);
            databaseHelper.insertUserData(BluetoothName,Username,NewPassword);
            Toast.makeText(requireContext(),"Password Updated",Toast.LENGTH_LONG).show();

        }
        else
        {
            Toast.makeText(requireContext(),"The Current Password you have entered is incorrect",Toast.LENGTH_LONG).show();
        }

    }
}
