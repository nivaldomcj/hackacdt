# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20170219082456) do

  # These are extensions that must be enabled in order to support this database
  enable_extension "plpgsql"

  create_table "acaos", force: :cascade do |t|
    t.string   "nome"
    t.string   "descricao"
    t.date     "dt_inicio"
    t.date     "dt_fim"
    t.integer  "organizacao_id"
    t.float    "vlr_hora"
    t.boolean  "status"
    t.datetime "created_at",     null: false
    t.datetime "updated_at",     null: false
    t.index ["organizacao_id"], name: "index_acaos_on_organizacao_id", using: :btree
  end

  create_table "organizacaos", force: :cascade do |t|
    t.string   "nome"
    t.string   "area_atuacao"
    t.string   "endereco"
    t.string   "cep"
    t.string   "cidade"
    t.string   "estado"
    t.datetime "created_at",   null: false
    t.datetime "updated_at",   null: false
    t.float    "lat"
    t.float    "log"
  end

  create_table "parceiros", force: :cascade do |t|
    t.string   "nome"
    t.string   "endereco"
    t.string   "cep"
    t.string   "cidade"
    t.string   "estado"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "rel_proj_ativos", force: :cascade do |t|
    t.integer  "acao_id"
    t.integer  "voluntario_id"
    t.datetime "created_at",    null: false
    t.datetime "updated_at",    null: false
    t.index ["acao_id"], name: "index_rel_proj_ativos_on_acao_id", using: :btree
    t.index ["voluntario_id"], name: "index_rel_proj_ativos_on_voluntario_id", using: :btree
  end

  create_table "users", force: :cascade do |t|
    t.string   "email",                  default: "", null: false
    t.string   "encrypted_password",     default: "", null: false
    t.string   "reset_password_token"
    t.datetime "reset_password_sent_at"
    t.datetime "remember_created_at"
    t.integer  "sign_in_count",          default: 0,  null: false
    t.datetime "current_sign_in_at"
    t.datetime "last_sign_in_at"
    t.inet     "current_sign_in_ip"
    t.inet     "last_sign_in_ip"
    t.datetime "created_at",                          null: false
    t.datetime "updated_at",                          null: false
    t.index ["email"], name: "index_users_on_email", unique: true, using: :btree
    t.index ["reset_password_token"], name: "index_users_on_reset_password_token", unique: true, using: :btree
  end

  create_table "voluntarios", force: :cascade do |t|
    t.string   "nome"
    t.integer  "idade"
    t.string   "email"
    t.string   "endereco"
    t.string   "cidade"
    t.float    "credito"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  add_foreign_key "acaos", "organizacaos"
  add_foreign_key "rel_proj_ativos", "acaos"
  add_foreign_key "rel_proj_ativos", "voluntarios"
end
