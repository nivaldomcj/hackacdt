class CreateVoluntarios < ActiveRecord::Migration[5.0]
  def change
    create_table :voluntarios do |t|
      t.string :nome_completo
      t.date :data_nasc
      t.string :profissao
      t.string :email
      t.string :telefone
      t.string :endereco
      t.string :cidade
      t.string :cep
      t.string :estado
      t.string :empresa_trabalha
      t.boolean :voluntario
      t.string :area_atuacao

      t.timestamps
    end
  end
end
