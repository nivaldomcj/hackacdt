class CreateAcaos < ActiveRecord::Migration[5.0]
  def change
    create_table :acaos do |t|
      t.string :nome
      t.string :descricao
      t.date :dt_inicio
      t.date :dt_fim
      t.references :organizacao, foreign_key: true
      t.float :vlr_hora
      t.boolean :status
      t.timestamps
    end
  end
end
