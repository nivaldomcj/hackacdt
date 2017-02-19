class CreateVoluntarios < ActiveRecord::Migration[5.0]
  def change
    create_table :voluntarios do |t|
      t.string :nome
      t.integer :idade
      t.string :email
      t.string :endereco
      t.string :cidade
      t.float :credito
      t.timestamps
    end
  end
end
