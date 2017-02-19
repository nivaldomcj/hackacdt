class OrganizacaosController < ApplicationController
  before_action :set_organizacao, only: [:show, :edit, :update, :destroy]
  before_action :authenticate_user!
  # GET /organizacaos
  # GET /organizacaos.json
  def index
    @organizacaos = Organizacao.all
    respond_to do |format|
      format.html
      format.json { render json: @organizacaos }
    end
  end

  # GET /organizacaos/1
  # GET /organizacaos/1.json
  def show
  end

  # GET /organizacaos/new
  def new
    @organizacao = Organizacao.new
  end

  # GET /organizacaos/1/edit
  def edit
  end

  # POST /organizacaos
  # POST /organizacaos.json
  def create
    @organizacao = Organizacao.new(organizacao_params)

    respond_to do |format|
      if @organizacao.save
        format.html { redirect_to @organizacao, notice: 'Organizacao was successfully created.' }
        format.json { render :show, status: :created, location: @organizacao }
      else
        format.html { render :new }
        format.json { render json: @organizacao.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /organizacaos/1
  # PATCH/PUT /organizacaos/1.json
  def update
    respond_to do |format|
      if @organizacao.update(organizacao_params)
        format.html { redirect_to @organizacao, notice: 'Organizacao was successfully updated.' }
        format.json { render :show, status: :ok, location: @organizacao }
      else
        format.html { render :edit }
        format.json { render json: @organizacao.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /organizacaos/1
  # DELETE /organizacaos/1.json
  def destroy
    @organizacao.destroy
    respond_to do |format|
      format.html { redirect_to organizacaos_url, notice: 'Organizacao was successfully destroyed.' }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_organizacao
      @organizacao = Organizacao.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def organizacao_params
      params.require(:organizacao).permit(:nome, :area_atuacao, :endereco, :cep, :cidade, :estado, :lat, :log)
    end
end
