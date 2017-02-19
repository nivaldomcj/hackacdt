class CreateOrganizacaos < ActiveRecord::Migration[5.0]
  def change
    create_table :organizacaos do |t|
      t.string :nome
      t.string :area_atuacao
      t.string :endereco
      t.string :cep
      t.string :cidade
      t.string :estado

      t.timestamps
    end
  end
end
