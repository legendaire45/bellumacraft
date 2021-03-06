package mod.legendaire45.entity.mobs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mod.legendaire45.common.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.AchievementList;
import net.minecraft.world.World;



public class EntityCheval extends EntityAnimal
{
	
	private static String cheval = CommonProxy.cheval;
    
    int tempsSaut; //declaration de notre fonction pour le temps entre deux saut
    
    public EntityCheval(World world)
    {
        super(world);
        texture = cheval;
        this.getNavigator().setAvoidsWater(true);
        float var2 = 0.25F;
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 0.38F));
        this.tasks.addTask(2, new EntityAILookIdle(this));
        this.tasks.addTask(3, new EntityAIMate(this, var2));
        this.tasks.addTask(4, new EntityAITempt(this, 0.3F, Item.wheat.itemID, false));
        this.tasks.addTask(5, new EntityAIFollowParent(this, 0.28F));
        this.tasks.addTask(6, new EntityAIWander(this, var2));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));

    }
    
    public int getMaxHealth()
    {
        return 30;
    }

    protected void updateAITasks()
    {
        super.updateAITasks();
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setBoolean("Saddle", this.getSaddled());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.setSaddled(par1NBTTagCompound.getBoolean("Saddle"));
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer par1EntityPlayer)
    {
        if (super.interact(par1EntityPlayer))
        {
            return true;
        }
        else if (this.getSaddled() && !this.worldObj.isRemote && (this.riddenByEntity == null || this.riddenByEntity == par1EntityPlayer))
        {
            par1EntityPlayer.mountEntity(this);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void jump(Boolean flag) {

		if (onGround && tempsSaut == 0) {
			super.jump();

			if (flag) {
				motionY += 0.1; //Permet de regler la hauteur du saut !
			}
			tempsSaut = 10;
		}
	}
	
    @SideOnly(Side.CLIENT)
	public void onLivingUpdate() {

		super.onLivingUpdate();

		if (tempsSaut > 0) {
			tempsSaut--;
		}

		if (riddenByEntity != null) {
			EntityPlayer entityPlayer = (EntityPlayer) riddenByEntity; //fonctions diverses ameliorant le maniabilite du mob
			rotationYaw = prevRotationYaw = entityPlayer.rotationYaw;
			EntityLiving par1EntityLiving = (EntityLiving) riddenByEntity;
            this.isJumping = Minecraft.getMinecraft().thePlayer.movementInput.jump; // Fonction initiant le saut lorsque on appuie sur espace	
		}
	}

	public void updateEntityActionState(){

		if (riddenByEntity != null && (riddenByEntity instanceof EntityLiving)) {

			EntityLiving par1EntityLiving = (EntityLiving) riddenByEntity;
			this.rotationPitch = 0;
			this.rotationYaw = prevRotationYaw = par1EntityLiving.rotationYaw;
			this.moveEntity(motionX, motionY, motionZ);

			if (inWater) {
				motionY -= riddenByEntity.motionY;
			}

			else if (onGround) {
				motionX += riddenByEntity.motionX * 5.5D; //vitesse du mob, changez a votre guise
				motionZ += riddenByEntity.motionZ * 5.5D;
			} else {
				motionX += riddenByEntity.motionX;
				motionZ += riddenByEntity.motionZ;
			}
			return;
		} else {
			super.updateEntityActionState();
			return;
		}
	}


    /**
     * Returns true if the horse is saddled.
     */
    public boolean getSaddled()
    {
        return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }
    
    /**
     * Set or remove the saddle of the pig.
     */
    public void setSaddled(boolean par1)
    {
        if (par1)
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)1));
        }
        else
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)0));
        }
    }
    
    public boolean isAIEnabled(){
    	   return this.riddenByEntity == null ? true : false;
    	}
    
    /**
     * Called when the mob is falling. Calculates and applies fall damage.
     */
    protected void fall(float par1)
    {
        super.fall(par1);

        if (par1 > 5.0F && this.riddenByEntity instanceof EntityPlayer)
        {
            ((EntityPlayer)this.riddenByEntity).triggerAchievement(AchievementList.flyPig);
        }
    }
    
    /**
     * Plays step sound at given x, y, z for the entity
     */
    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        this.playSound("mob.cow.step", 0.15F, 1.0F);
    }
    
    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
    	return "mob.horse.horsegrunt";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
    	return "mob.horse.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
    	return "mob.horse.hurt";
    }


	public float getBlockPathWeight(int i, int j, int k)
    {
        return worldObj.getLightBrightness(i, j, k) - 0.5F;
    }

    /**
     * This function is used when two same-species animals in 'love mode' breed to generate the new baby animal.
     */
    public EntityCheval spawnBabyAnimal(EntityAgeable par1EntityAgeable)
    {
        return new EntityCheval(this.worldObj);
    }

    public EntityAgeable createChild(EntityAgeable par1EntityAgeable)
    {
        return this.spawnBabyAnimal(par1EntityAgeable);
    }

}

