class ParceirosController < ApplicationController
  before_action :set_parceiro, only: [:show, :edit, :update, :destroy]

  # GET /parceiros
  # GET /parceiros.json
  def index
    @parceiros = Parceiro.all
  end

  # GET /parceiros/1
  # GET /parceiros/1.json
  def show
  end

  # GET /parceiros/new
  def new
    @parceiro = Parceiro.new
  end

  # GET /parceiros/1/edit
  def edit
  end

  # POST /parceiros
  # POST /parceiros.json
  def create
    @parceiro = Parceiro.new(parceiro_params)

    respond_to do |format|
      if @parceiro.save
        format.html { redirect_to @parceiro, notice: 'Parceiro was successfully created.' }
        format.json { render :show, status: :created, location: @parceiro }
      else
        format.html { render :new }
        format.json { render json: @parceiro.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /parceiros/1
  # PATCH/PUT /parceiros/1.json
  def update
    respond_to do |format|
      if @parceiro.update(parceiro_params)
        format.html { redirect_to @parceiro, notice: 'Parceiro was successfully updated.' }
        format.json { render :show, status: :ok, location: @parceiro }
      else
        format.html { render :edit }
        format.json { render json: @parceiro.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /parceiros/1
  # DELETE /parceiros/1.json
  def destroy
    @parceiro.destroy
    respond_to do |format|
      format.html { redirect_to parceiros_url, notice: 'Parceiro was successfully destroyed.' }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_parceiro
      @parceiro = Parceiro.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def parceiro_params
      params.require(:parceiro).permit(:nome, :endereco, :cep, :cidade, :estado)
    end
end
