class CreateRelProjAtivos < ActiveRecord::Migration[5.0]
  def change
    create_table :rel_proj_ativos do |t|
      t.references :acao, foreign_key: true
      t.references :voluntario, foreign_key: true

      t.timestamps
    end
  end
end
