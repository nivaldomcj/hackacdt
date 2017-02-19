class AddLogToOrganizacaos < ActiveRecord::Migration[5.0]
  def change
    add_column :organizacaos, :log, :float
  end
end
