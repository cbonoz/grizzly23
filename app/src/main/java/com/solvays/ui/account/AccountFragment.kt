package com.solvays.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.solanamobile.seedvault.Wallet
import com.solvays.R
import com.solvays.databinding.FragmentAccountBinding
import com.solvays.util.PaymentHelper.Companion.DEFAULT_ADDRESS

class AccountFragment : Fragment() {

    private lateinit var balanceText: TextView
    private var _binding: FragmentAccountBinding? = null
    private var wallet: Wallet? = null
    private var balance: Float? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val view: View = binding.root

        // TODO: update the xml layout file and render the user's account balance.
        //  Enable the user to update their payment address and refresh the balance

        balanceText = view.findViewById<TextView>(R.id.balance_text)
        setBalance(null)
        val addressText = view.findViewById<EditText>(R.id.address_text)
        addressText.setText(DEFAULT_ADDRESS)

        return view
    }


    fun setBalance(balance: Float?) {
        if (balance != null) {
            balanceText.visibility = View.VISIBLE
            balanceText.text = "$balance sol"
        } else {
            balanceText.visibility = View.GONE
        }
        this.balance = balance;
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}