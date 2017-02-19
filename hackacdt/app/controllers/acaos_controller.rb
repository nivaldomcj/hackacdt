class AcaosController < ApplicationController
  before_action :set_acao, only: [:show, :edit, :update, :destroy]
  before_action :authenticate_user!
  # GET /acaos
  # GET /acaos.json
  def index
    @acaos = Acao.all
  end

  # GET /acaos/1
  # GET /acaos/1.json
  def show
  end

  # GET /acaos/new
  def new
    @acao = Acao.new
  end

  # GET /acaos/1/edit
  def edit
  end

  # POST /acaos
  # POST /acaos.json
  def create
    @acao = Acao.new(acao_params)

    respond_to do |format|
      if @acao.save
        format.html { redirect_to @acao, notice: 'Acao was successfully created.' }
        format.json { render :show, status: :created, location: @acao }
      else
        format.html { render :new }
        format.json { render json: @acao.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /acaos/1
  # PATCH/PUT /acaos/1.json
  def update
    respond_to do |format|
      if @acao.update(acao_params)
        format.html { redirect_to @acao, notice: 'Acao was successfully updated.' }
        format.json { render :show, status: :ok, location: @acao }
      else
        format.html { render :edit }
        format.json { render json: @acao.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /acaos/1
  # DELETE /acaos/1.json
  def destroy
    @acao.destroy
    respond_to do |format|
      format.html { redirect_to acaos_url, notice: 'Acao was successfully destroyed.' }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_acao
      @acao = Acao.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def acao_params
      params.require(:acao).permit(:nome, :descricao, :dt_inicio, :dt_fim, :organizacao_id)
    end
end
