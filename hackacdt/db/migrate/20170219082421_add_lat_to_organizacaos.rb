class AddLatToOrganizacaos < ActiveRecord::Migration[5.0]
  def change
    add_column :organizacaos, :lat, :float
  end
end
