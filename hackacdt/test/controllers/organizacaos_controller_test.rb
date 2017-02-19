require 'test_helper'

class OrganizacaosControllerTest < ActionDispatch::IntegrationTest
  setup do
    @organizacao = organizacaos(:one)
  end

  test "should get index" do
    get organizacaos_url
    assert_response :success
  end

  test "should get new" do
    get new_organizacao_url
    assert_response :success
  end

  test "should create organizacao" do
    assert_difference('Organizacao.count') do
      post organizacaos_url, params: { organizacao: { area_atuacao: @organizacao.area_atuacao, cep: @organizacao.cep, cidade: @organizacao.cidade, endereco: @organizacao.endereco, estado: @organizacao.estado, nome: @organizacao.nome } }
    end

    assert_redirected_to organizacao_url(Organizacao.last)
  end

  test "should show organizacao" do
    get organizacao_url(@organizacao)
    assert_response :success
  end

  test "should get edit" do
    get edit_organizacao_url(@organizacao)
    assert_response :success
  end

  test "should update organizacao" do
    patch organizacao_url(@organizacao), params: { organizacao: { area_atuacao: @organizacao.area_atuacao, cep: @organizacao.cep, cidade: @organizacao.cidade, endereco: @organizacao.endereco, estado: @organizacao.estado, nome: @organizacao.nome } }
    assert_redirected_to organizacao_url(@organizacao)
  end

  test "should destroy organizacao" do
    assert_difference('Organizacao.count', -1) do
      delete organizacao_url(@organizacao)
    end

    assert_redirected_to organizacaos_url
  end
end
