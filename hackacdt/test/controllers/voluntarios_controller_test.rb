require 'test_helper'

class VoluntariosControllerTest < ActionDispatch::IntegrationTest
  setup do
    @voluntario = voluntarios(:one)
  end

  test "should get index" do
    get voluntarios_url
    assert_response :success
  end

  test "should get new" do
    get new_voluntario_url
    assert_response :success
  end

  test "should create voluntario" do
    assert_difference('Voluntario.count') do
      post voluntarios_url, params: { voluntario: { area_atuacao: @voluntario.area_atuacao, cep: @voluntario.cep, cidade: @voluntario.cidade, data_nasc: @voluntario.data_nasc, email: @voluntario.email, empresa_trabalha: @voluntario.empresa_trabalha, endereco: @voluntario.endereco, estado: @voluntario.estado, nome_completo: @voluntario.nome_completo, profissao: @voluntario.profissao, telefone: @voluntario.telefone, voluntario: @voluntario.voluntario } }
    end

    assert_redirected_to voluntario_url(Voluntario.last)
  end

  test "should show voluntario" do
    get voluntario_url(@voluntario)
    assert_response :success
  end

  test "should get edit" do
    get edit_voluntario_url(@voluntario)
    assert_response :success
  end

  test "should update voluntario" do
    patch voluntario_url(@voluntario), params: { voluntario: { area_atuacao: @voluntario.area_atuacao, cep: @voluntario.cep, cidade: @voluntario.cidade, data_nasc: @voluntario.data_nasc, email: @voluntario.email, empresa_trabalha: @voluntario.empresa_trabalha, endereco: @voluntario.endereco, estado: @voluntario.estado, nome_completo: @voluntario.nome_completo, profissao: @voluntario.profissao, telefone: @voluntario.telefone, voluntario: @voluntario.voluntario } }
    assert_redirected_to voluntario_url(@voluntario)
  end

  test "should destroy voluntario" do
    assert_difference('Voluntario.count', -1) do
      delete voluntario_url(@voluntario)
    end

    assert_redirected_to voluntarios_url
  end
end
