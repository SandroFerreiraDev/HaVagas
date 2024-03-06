package com.example.havagas

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.havagas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // VISIBILIDADE DO TELEFONE CELULAR
        val visibilityTelefoneCelular = binding.textInputLayoutTelefoneCelular
        val adicionarTelefoneCelular =
            binding.checkBoxAdicionarTelefoneCelular
        adicionarTelefoneCelular.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                visibilityTelefoneCelular.visibility = View.VISIBLE
            } else {
                visibilityTelefoneCelular.visibility = View.GONE
            }
        }
        // VISIBILIDADE DO TELEFONE CELULAR

        // VISIBILIDADE DA FORMAÇÃO
        fun showHideCamposAdicionais(formacaoSelecionada: String) {
            with(binding) {
                binding.editTextAnoFormatura.visibility = if (formacaoSelecionada in arrayOf(
                        "Fundamental",
                        "Médio"
                    )
                ) View.VISIBLE else View.GONE
                binding.editTextAnoConclusao.visibility = if (formacaoSelecionada in arrayOf(
                        "Graduação",
                        "Especialização",
                        "Mestrado",
                        "Doutorado"
                    )
                ) View.VISIBLE else View.GONE
                binding.editTextInstituicao.visibility = if (formacaoSelecionada in arrayOf(
                        "Graduação",
                        "Especialização",
                        "Mestrado",
                        "Doutorado"
                    )
                ) View.VISIBLE else View.GONE
                binding.editTextTituloMonografia.visibility = if (formacaoSelecionada in arrayOf(
                        "Mestrado",
                        "Doutorado"
                    )
                ) View.VISIBLE else View.GONE
                binding.editTextOrientador.visibility = if (formacaoSelecionada in arrayOf(
                        "Mestrado",
                        "Doutorado"
                    )
                ) View.VISIBLE else View.GONE
            }
        }

        val formacaoAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.formacao_array,
            android.R.layout.simple_spinner_item)
            formacaoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerFormacao.adapter = formacaoAdapter

            binding.spinnerFormacao.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val formacaoSelecionada = parent?.getItemAtPosition(position).toString()
                    showHideCamposAdicionais(formacaoSelecionada)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        // VISIBILIDADE DA FORMAÇÃO

        fun isValidEmail(email: String): Boolean {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        // BOTÃO LIMPAR
        fun limparCampos() {
            with(binding) {
                editTextNomeCompleto.text.clear()
                editTextEmail.text.clear()
                checkBoxReceberEmails.isChecked = false
                editTextTelefone.text = null
                radioGroupTipoTelefone.clearCheck()
                checkBoxAdicionarTelefoneCelular.isChecked = false
                editTextTelefoneCelular.text = null
                radioGroupSexo.clearCheck()
                editTextDataNascimento.text.clear()
                spinnerFormacao.setSelection(0)
                editTextAnoFormatura.text.clear()
                editTextAnoConclusao.text.clear()
                editTextInstituicao.text.clear()
                editTextTituloMonografia.text.clear()
                editTextOrientador.text.clear()
                editTextVagasInteresse.text.clear()
            }
        }
        binding.btnLimpar.setOnClickListener {
            limparCampos()}
        // BOTÃO LIMPAR

        // BOTÃO SALVAR
        binding.btnSalvar.setOnClickListener {
            val nomeCompleto = binding.editTextNomeCompleto.text.toString()
            val email = binding.editTextEmail.text.toString()

            if (!isValidEmail(email)) {
                Toast.makeText(this, "Por favor, insira um endereço de e-mail válido.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val receberEmails = binding.checkBoxReceberEmails.isChecked
            val telefone = binding.editTextTelefone.text.toString()
            val tipoTelefone = when (binding.radioGroupTipoTelefone.checkedRadioButtonId) {
                R.id.radioButtonResidencial -> "Residencial"
                R.id.radioButtonComercial -> "Comercial"
                else -> ""
            }
            val telefoneCelular = binding.editTextTelefoneCelular.text.toString()
            val sexo = when (binding.radioGroupSexo.checkedRadioButtonId) {
                R.id.radioButtonMasculino -> "Masculino"
                R.id.radioButtonFeminino -> "Feminino"
                R.id.radioButtonOutro -> "Outro"
                else -> ""
            }
            val dataNascimento = binding.editTextDataNascimento.text.toString()
            val vagasInteresse = binding.editTextVagasInteresse.text.toString()
            val formacao = binding.spinnerFormacao.selectedItem.toString()
            val anoFormatura = binding.editTextAnoFormatura.text.toString()
            val anoConclusao = binding.editTextAnoConclusao.text.toString()
            val instituicao = binding.editTextInstituicao.text.toString()
            val tituloMonografia = binding.editTextTituloMonografia.text.toString()
            val orientador = binding.editTextOrientador.text.toString()

            val stringPopUp = arrayOf(nomeCompleto,
                email,
                receberEmails,
                telefone,
                tipoTelefone,
                telefoneCelular,
                sexo,
                dataNascimento,
                formacao,
                anoFormatura,
                anoConclusao,
                instituicao,
                tituloMonografia,
                orientador,
                vagasInteresse,)


            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, stringPopUp)

            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("Usuário")
            alertDialogBuilder.setCancelable(true)
            alertDialogBuilder.setAdapter(adapter) { dialog, which ->
                val clickedString = stringPopUp[which]
                Toast.makeText(this, "Você clicou em: $clickedString", Toast.LENGTH_SHORT).show()
            }

            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
            }
        // BOTÃO SALVAR
        }
    }

